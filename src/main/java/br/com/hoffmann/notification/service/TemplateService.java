package br.com.hoffmann.notification.service;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import br.com.hoffmann.notification.domain.request.TemplateRequest;
import br.com.hoffmann.notification.domain.response.TemplateResponse;
import br.com.hoffmann.notification.entity.Template;
import br.com.hoffmann.notification.repository.TemplateRepository;
import br.com.hoffmann.notification.service.auxiliar.AuxiliarService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional(value = "transactionManager")
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private AuxiliarService auxiliar;

    public void createTemplate(TemplateRequest request) {
        auxiliar.criaTemplate(request);
    }

    public void deletaTemplate(Long templateID){ templateRepository.deleteById(templateID);}

    public void updateTemplate(TemplateRequest request, Long id) throws NotFoundException {
        Optional<Template> template = templateRepository.findById(id);
        if (!template.isPresent()) {
            throw new NotFoundException(format("Template [%s] não encontrado", id));
        }
        auxiliar.atualizaTemplate(request, template.get());
    }

    public List<TemplateResponse> buscaTemplates() {
        List<Template> template = templateRepository.findAll();
        return template.stream().map(TemplateResponse::new).collect(Collectors.toList());
    }


    public TemplateResponse TemplateByName(String templateName, TemplateType templateType) throws NotFoundException {
        Optional<Template> template = templateRepository.findByTemplateNameAndTemplateType(templateName, templateType);
        if (!template.isPresent()) {
            throw new NotFoundException(format("Nome do template [%s] não encontrado", templateName));
        }
        return new TemplateResponse(template.get());
    }
}
