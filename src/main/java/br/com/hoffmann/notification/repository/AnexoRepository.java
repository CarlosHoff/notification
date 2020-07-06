package br.com.hoffmann.notification.repository;

import br.com.hoffmann.notification.entity.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    Optional<Anexo> findAnexoByNomeAnexo(String nomeAnexo);
}
