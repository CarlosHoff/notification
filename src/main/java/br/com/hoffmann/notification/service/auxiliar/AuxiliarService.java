package br.com.hoffmann.notification.service.auxiliar;

import br.com.hoffmann.notification.client.UsuarioClient;
import br.com.hoffmann.notification.client.response.UsuarioResponse;
import br.com.hoffmann.notification.domain.enums.TemplateType;
import br.com.hoffmann.notification.domain.request.TemplateRequest;
import br.com.hoffmann.notification.entity.Anexo;
import br.com.hoffmann.notification.entity.Template;
import br.com.hoffmann.notification.repository.AnexoRepository;
import br.com.hoffmann.notification.repository.TemplateRepository;
import javassist.NotFoundException;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static java.lang.String.format;

@Component
public class AuxiliarService {

    @Value("${email.smtp}")
    private String smtp;

    @Value("${email.remetente}")
    private String remetente;

    @Value("${email.usuario}")
    private String user;

    @Value("${email.senha}")
    private String password;

    @Value("${email.porta}")
    private Integer porta;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private AnexoRepository anexoRepository;

    private static final String TEMPLATE_NOME_EMAIL = "email-novo-cadastro";

    public void criaTemplate(TemplateRequest request) {
        Template template = new Template();
        template.setTemplateName(request.getTemplateName());
        template.setTemplateDesciption(request.getTemplateDesciption());
        template.setTemplateSubject(request.getTemplateSubject());
        template.setTemplateType(request.getTemplateType());
        template.setTemplateMessage(request.getTemplateMessage());
        salvaTemplate(template);
    }

    public void atualizaTemplate(TemplateRequest request, Template template) {
        template.setTemplateName(request.getTemplateName());
        template.setTemplateDesciption(request.getTemplateDesciption());
        template.setTemplateSubject(request.getTemplateSubject());
        template.setTemplateType(request.getTemplateType());
        template.setTemplateMessage(request.getTemplateMessage());
        salvaTemplate(template);
    }

    private void salvaTemplate(Template template) {
        templateRepository.save(template);
    }

    private UsuarioResponse getUsuario(Long usuarioID) {
        return usuarioClient.buscaUsuario(usuarioID);
    }

    private Template getTemplateByNameAndType() throws NotFoundException {
        Optional<Template> template = templateRepository.findByTemplateNameAndTemplateType(TEMPLATE_NOME_EMAIL, TemplateType.EMAIL);
        if (!template.isPresent()) {
            throw new NotFoundException(format("Template [%s] não encontrado", TEMPLATE_NOME_EMAIL));
        }
        return template.get();
    }

    public void sendMailWithoutAttach(Long usuarioID) throws EmailException, NotFoundException {
        UsuarioResponse usuario = getUsuario(usuarioID);
        Template template = getTemplateByNameAndType();

        SimpleEmail email = new SimpleEmail();
        email.setHostName(smtp);
        email.addTo(usuario.getEmail(), usuario.getNome());
        email.setFrom(remetente);
        email.setSubject(template.getTemplateSubject());
        email.setMsg(template.getTemplateMessage());
        email.setAuthentication(user, password);
        email.setSmtpPort(porta);
        email.setSSL(true);
        email.setTLS(true);
        email.send();
    }

    public void sendMailWithAttach(Long usuarioID, String nomeAnexo) throws EmailException, MalformedURLException, NotFoundException {
        UsuarioResponse usuario = getUsuario(usuarioID);
        Template template = getTemplateByNameAndType();

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(smtp);
        email.addTo(usuario.getEmail(), usuario.getNome());
        email.setFrom(remetente);
        email.setSubject(template.getTemplateSubject());
        email.setMsg(template.getTemplateMessage());
        email.setAuthentication(user, password);
        email.setSmtpPort(porta);
        email.setSSL(true);
        email.setTLS(true);

        email.attach(pegaAnexo(nomeAnexo));

        email.send();
    }

    private EmailAttachment pegaAnexo(String nomeAnexo) throws MalformedURLException, NotFoundException {
        Anexo anexo = pegaAnexoPeloNome(nomeAnexo);
        EmailAttachment attachment = new EmailAttachment();

        if (anexo.getUrlAnexo() != null) {
            attachment.setURL(new URL(anexo.getUrlAnexo()));
        } else if (anexo.getPathAnexo() != null) {
            attachment.setPath(anexo.getPathAnexo());
        }
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(anexo.getDescricaoAnexo());
        attachment.setName(anexo.getNomeAnexo());
        return attachment;
    }

    private Anexo pegaAnexoPeloNome(String nomeAnexo) throws NotFoundException {
        Optional<Anexo> anexo = anexoRepository.findAnexoByNomeAnexo(nomeAnexo);
        if (!anexo.isPresent()) {
            throw new NotFoundException(format("Anexo [%s] não encontrado", nomeAnexo));
        }
        return anexo.get();
    }

}
