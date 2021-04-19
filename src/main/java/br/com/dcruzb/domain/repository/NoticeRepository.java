package br.com.dcruzb.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dcruzb.domain.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> { //, PagingAndSortingRepository<Notice, Long>
	public List<Notice> findByTitle(String title);
}
