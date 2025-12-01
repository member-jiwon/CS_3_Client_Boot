package com.kedu.project.pregnancy_journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.project.file_info.FileService;

@Service
@Transactional
public class PregnancyJournalFacadeService {

	@Autowired
    private PregnancyJournalService pregnancyJournalService;
	@Autowired
	private FileService fServ;
	
	
	//인서트
	public int postDiary(PregnancyJournalDTO dto, String imageSysListJson) {
		int target_seq =pregnancyJournalService.postDiary(dto); // 다이어리 입력
		fServ.confirmImg(imageSysListJson, target_seq);
		
		return target_seq;
	}
	
	
	//삭제
	public int deleteTargetDTO(String id, int journal_seq) {
		int deleted = pregnancyJournalService.deleteTargetDTO(id, journal_seq);
		System.out.println("글 삭제 결과 = " + deleted);

		int fileDeleted = fServ.deleteAllFile(journal_seq, id, "diary/img");
		System.out.println("파일 삭제 결과 = " + fileDeleted);

		return fileDeleted;
	}
	
}
