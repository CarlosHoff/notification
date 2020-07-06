package br.com.hoffmann.notification.client.response;

import br.com.hoffmann.notification.client.enums.TipoDeUsuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioResponse implements Serializable {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Tipo de usuario")
    private TipoDeUsuario tipoDeUsuario;

    @ApiModelProperty(value = "Status")
    private Character status;

    @ApiModelProperty(value = "Estado")
    private String estado;

    @ApiModelProperty(value = "Sigla")
    private String sigla;

    @ApiModelProperty(value = "Cidade")
    private String cidade;

    @ApiModelProperty(value = "Rua")
    private String rua;

    @ApiModelProperty(value = "Numero")
    private Short numero;

    @ApiModelProperty(value = "Bairro")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    private String cep;

    @ApiModelProperty(value = "Complemento")
    private String complemento;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Celular")
    private String celular;

    @ApiModelProperty(value = "Email")
    private String email;

}
