package br.com.dcruzb.domain.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dcruzb.domain.model.Notice;
import br.com.dcruzb.domain.repository.NoticeRepository;
import br.com.dcruzb.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	public Notice findOrFail(Long noticeId) throws EntityNotFoundException {
		return noticeRepository.findById(noticeId).orElseThrow(
					() -> new EntityNotFoundException("Notice " + noticeId + " not found!") // TODO: Create a specific exception
				);
	}
	
	@Transactional
	public Notice save(Notice notice) {
		return noticeRepository.save(notice);
	}
	
	@Transactional
	public void delete(Long noticeId) throws EntityNotFoundException {
		try {
			noticeRepository.deleteById(noticeId);
			noticeRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Notice " + noticeId + " not found!"); // TODO: Create a specific exception
		} catch (DataIntegrityViolationException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new EntityNotFoundException("Its is not possible to delete Notice " + noticeId); // TODO: Create a specific exception
		}
	}
}
