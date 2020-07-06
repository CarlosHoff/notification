package br.com.hoffmann.notification.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "ANEXO")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANEXO_ID")
    private Long anexoID;

    @Column(name = "URL_ANEXO", length = 100, nullable = false)
    private String urlAnexo;

    @Column(name = "PATH_ANEXO", length = 100, nullable = false)
    private String pathAnexo;

    @Column(name = "DESCRICAO_ANEXO", length = 100, nullable = false)
    private String descricaoAnexo;

    @Column(name = "NOME_ANEXO", length = 50, nullable = false)
    private String nomeAnexo;

}
