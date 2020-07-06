package br.com.hoffmann.notification.entity;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "TEMPLATE")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_TEMPLATE")
    @SequenceGenerator(sequenceName = "SQ_TEMPLATE", allocationSize = 1, name = "SQ_TEMPLATE")
    @Column(name = "TEMPLATE_ID")
    private Long templateID;

    @Column(name = "TEMPLATE_NAME", length = 50, nullable = false)
    private String templateName;

    @Column(name = "TEMPLATE_DESCRIPTION", length = 50, nullable = false)
    private String templateDesciption;

    @Column(name = "TEMPLATE_SUBJECT", length = 50, nullable = false)
    private String templateSubject;

    @Column(name = "TEMPLATE_MESSAGE", length = 2000, nullable = false)
    private String templateMessage;

    @Column(name = "TEMPLATE_TYPE", nullable = false)
    private TemplateType templateType;

}
