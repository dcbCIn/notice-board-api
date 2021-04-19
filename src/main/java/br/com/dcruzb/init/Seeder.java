package br.com.dcruzb.init;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dcruzb.domain.model.Notice;
import br.com.dcruzb.domain.service.NoticeService;

@Component
public class Seeder {
	@Autowired
	private NoticeService noticeService;
	
	public void seed() {
		Notice notice = new Notice();
		notice.setTitle("Notice 1");
		notice.setDescription("The description of the notice");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 2");
		notice.setDescription("Another description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 3");
		notice.setDescription("One more description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 4");
		notice.setDescription("One more description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 5");
		notice.setDescription("One more description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 6");
		notice.setDescription("One more description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
		
		notice = new Notice();
		notice.setTitle("Notice 7");
		notice.setDescription("One more description");
		notice.setPublicationDate(LocalDateTime.now());
		
		noticeService.save(notice);
	}
}
