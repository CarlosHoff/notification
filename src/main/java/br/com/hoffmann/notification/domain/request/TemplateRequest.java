package br.com.hoffmann.notification.domain.request;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class TemplateRequest implements Serializable {

    @ApiModelProperty(value = "Nome do template")
    @NotNull
    @Size(max = 50)
    private String templateName;

    @ApiModelProperty(value = "Descrição do template")
    @NotNull
    @Size(max = 100)
    private String templateDesciption;

    @ApiModelProperty(value = "Template subject")
    @NotNull
    @Size(max = 50)
    private String templateSubject;

    @ApiModelProperty(value = "Mensagem do template")
    @NotNull
    @Size(max = 1000)
    private String templateMessage;

    @ApiModelProperty(value = "Tipo do template")
    @NotNull
    private TemplateType templateType;

    public interface createRequest {
    }

    public interface updateRequest {
    }
}
