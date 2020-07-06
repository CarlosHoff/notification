package br.com.hoffmann.notification.repository;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import br.com.hoffmann.notification.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findByTemplateNameAndTemplateType(String templateName, TemplateType templateType);
}
