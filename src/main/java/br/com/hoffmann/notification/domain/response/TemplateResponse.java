package br.com.hoffmann.notification.domain.response;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import br.com.hoffmann.notification.entity.Template;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class TemplateResponse implements Serializable {

    private String templateName;

    @ApiModelProperty(value = "Descrição do template")
    private String templateDesciption;

    @ApiModelProperty(value = "Template subject")
    private String templateSubject;

    @ApiModelProperty(value = "Mensagem do template")
    private String templateMessage;

    @ApiModelProperty(value = "Tipo de template")
    private TemplateType templateType;

    public TemplateResponse() {
    }

    public TemplateResponse(Template template) {
        this.templateName = template.getTemplateName();
        this.templateDesciption = template.getTemplateDesciption();
        this.templateSubject = template.getTemplateSubject();
        this.templateMessage = template.getTemplateMessage();
        this.templateType = template.getTemplateType();
    }
}
