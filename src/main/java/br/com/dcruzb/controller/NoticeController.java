package br.com.dcruzb.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dcruzb.domain.model.Notice;
import br.com.dcruzb.domain.repository.NoticeRepository;
import br.com.dcruzb.domain.service.NoticeService;
import br.com.dcruzb.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping()
	public Page<Notice> list(@PageableDefault(size=5) Pageable page) {
		// TODO: create DTO to return safely
		return noticeRepository.findAll(page);
	}
	
	@GetMapping("/{noticeId}")
	public ResponseEntity<Notice> findById(@PathVariable Long noticeId) {
		try {
			return ResponseEntity.ok(noticeService.findOrFail(noticeId));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid Notice notice) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.save(notice));
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to save the notice!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
	
	@PutMapping("/{noticeId}")
	public ResponseEntity<?> update(@PathVariable Long noticeId, @RequestBody @Valid Notice notice) {
		try {
			Notice storedNotice = noticeService.findOrFail(noticeId);
			
			storedNotice.setTitle(notice.getTitle());
			storedNotice.setDescription(notice.getDescription());
			storedNotice.setPublicationDate(notice.getPublicationDate());
			storedNotice.setViewDate(notice.getViewDate());
			
			return ResponseEntity.ok(noticeService.save(storedNotice));
		} catch (EntityNotFoundException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getMessage(), LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to update the notice!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
	
	@DeleteMapping("/{noticeId}")
	public ResponseEntity<?> delete(@PathVariable Long noticeId) {
		try {
			noticeService.delete(noticeId);
			
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getMessage(), LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to update the notice!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
}
