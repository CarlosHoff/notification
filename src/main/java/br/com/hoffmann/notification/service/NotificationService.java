package br.com.hoffmann.notification.service;

import br.com.hoffmann.notification.service.auxiliar.AuxiliarService;
import javassist.NotFoundException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;

@Service
@Transactional(value = "transactionManager")
public class NotificationService {

    @Autowired
    private AuxiliarService auxiliar;
    
    public void enviaEmailSimples(Long usuarioID) throws EmailException, NotFoundException {
        auxiliar.sendMailWithoutAttach(usuarioID);
    }

    public void enviaEmailComAnexo(Long usuarioID, String nomeAnexo) throws EmailException, NotFoundException, MalformedURLException {
        auxiliar.sendMailWithAttach(usuarioID, nomeAnexo);
    }
}
