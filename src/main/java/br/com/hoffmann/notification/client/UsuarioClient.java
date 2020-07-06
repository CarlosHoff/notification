package br.com.hoffmann.notification.client;

import br.com.hoffmann.notification.client.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "${usuario.client}", name = "UsuarioClient")
public interface UsuarioClient {

    @RequestMapping(value = "/buscaUsuarios", method = RequestMethod.GET)
    List<UsuarioResponse> buscaUsuarios();

    @RequestMapping(value = "/buscaUsuario/{id}", method = RequestMethod.GET)
    UsuarioResponse buscaUsuario(@PathVariable(value = "id") Long id);
}
