package com.site.site;

import com.site.site.model.entity.Question;
import com.site.site.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SiteApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	@DisplayName("JPA 테스트")
	void givenJPA_whenSaveRepository_thenSaveQuery() {
		// Given
		Question TestQ1 = new Question();
		TestQ1.setTitle("Test Title One");
		TestQ1.setContent("Test Content One");
		TestQ1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(TestQ1);

		Question TestQ2 = new Question();
		TestQ2.setTitle("Test Title Two");
		TestQ2.setContent("Test Content Two");
		TestQ2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(TestQ2);
		// When & Then
	}

	@Test
	@DisplayName("id로 제목 조회")
	void Test_FindByTitle() {
		Question q = this.questionRepository.findByTitle("Test Title One");
		assertEquals(2, q.getId());
	}

	@Test
	@DisplayName("id로 제목 및 내용 조회")
	void Test_FindByTitleAndContent() {
		Question q = this.questionRepository.findByTitleAndContent(
				"Test Title One", "Test Content One");
		assertEquals(2, q.getId());
	}



}
