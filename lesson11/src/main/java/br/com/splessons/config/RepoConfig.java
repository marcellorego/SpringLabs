package br.com.splessons.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.com.splessons.persistence.repo")
@EntityScan("br.com.splessons.persistence.model")
public class RepoConfig {
}
