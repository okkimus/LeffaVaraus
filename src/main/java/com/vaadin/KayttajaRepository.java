package com.vaadin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KayttajaRepository extends JpaRepository<Kayttaja, Long> {
}
