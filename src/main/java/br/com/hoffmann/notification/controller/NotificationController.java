package br.com.hoffmann.notification.controller;

import br.com.hoffmann.notification.service.NotificationService;
import javassist.NotFoundException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.MalformedURLException;

@Controller
@RequestMapping(path = "/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/enviaEmailSemAnexo", method = RequestMethod.POST)
    public void enviaEmailSemAnexo(@Param(value = "usuarioID") Long usuarioID) {
        try {
            notificationService.enviaEmailSimples(usuarioID);
        } catch (EmailException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/enviaEmailComAnexo", method = RequestMethod.POST)
    public void enviaEmailComAnexo(@Param(value = "usuarioID") Long usuarioID,
                                   @Param(value = "nomeAnexo") String nomeAnexo) {
        try {
            notificationService.enviaEmailComAnexo(usuarioID, nomeAnexo);
        } catch (EmailException | NotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
