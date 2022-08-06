package com.uc_it4045.assignment_tracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.uc_it4045.assignment_tracker.controllers.AssignmentTrackerController;
import com.uc_it4045.assignment_tracker.dao.IAssignmentDAO;
import com.uc_it4045.assignment_tracker.dao.UserRepository;
import com.uc_it4045.assignment_tracker.dto.Assignment;
import com.uc_it4045.assignment_tracker.dto.AuthUser;
import com.uc_it4045.assignment_tracker.service.AssignmentService;
import com.uc_it4045.assignment_tracker.service.IAssignmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AssignmentTrackerApplicationTests {

	private IAssignmentService assignmentService;
	private Assignment assignment = new Assignment();
	private List<Assignment> herosAssignments = new ArrayList<>();
	private AuthUser hero = new AuthUser();

	@MockBean
	private IAssignmentDAO assignmentDAO;
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private AssignmentTrackerController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void fetchAssignmentById_returnsNewAssignmentForID19() throws Exception {
		givenAssignmentsAreAvailable();
		whenAssignment19AddedIsNewAssignment();
		whenSearchAssignmentWithID19();
		thenReturnNewAssignmentForID19();
	}

	private void thenReturnNewAssignmentForID19() {
		String title = assignment.getTitle();
		assertEquals("New Assignment", title);
	}

	private void whenSearchAssignmentWithID19() {
		assignment = assignmentService.fetchById(19);
	}

	private void whenAssignment19AddedIsNewAssignment() {
		Assignment newAssignment = new Assignment();
		newAssignment.setId(19);
		newAssignment.setTitle("New Assignment");
		Mockito.when(assignmentDAO.fetch(19)).thenReturn(newAssignment);
	}

	private void givenAssignmentsAreAvailable() throws Exception {
		Mockito.when(assignmentDAO.save(assignment)).thenReturn(assignment);
		Mockito.when(assignmentDAO.fetchUserAssignments(hero.getId())).thenReturn(herosAssignments);
		assignmentService = new AssignmentService(assignmentDAO, userRepository);
	}

	@Test
	void onlyAssignmentsBelongingToUserShouldReturn() throws Exception {
		givenAssignmentsAreAvailable();
		whenHeroUserIsCreated();
		whenAssignmentsAreCreated();
		whenFetchingUserAssignments();
		thenReturnUserAssignments();
	}

	private void whenHeroUserIsCreated() {
		hero.setId(123);
		hero.setUsername("hero");
		Mockito.when(userRepository.findByUsername(hero.getUsername())).thenReturn(hero);
	}

	private void whenAssignmentsAreCreated() throws IOException {
		int heroId = hero.getId();
		Assignment hero01 = new Assignment();
		Assignment hero02 = new Assignment();
		Assignment hero03 = new Assignment();
		hero01.setUser(hero);
		hero02.setUser(hero);
		hero03.setUser(hero);
		herosAssignments.add(hero01);
		herosAssignments.add(hero02);
		herosAssignments.add(hero03);
		Mockito.when(assignmentDAO.fetchUserAssignments(heroId)).thenReturn(herosAssignments);
	}

	private void whenFetchingUserAssignments() throws IOException {
		herosAssignments = assignmentService.fetchUserAssignments(hero.getUsername());
	}

	private void thenReturnUserAssignments() throws IOException {
		List<Assignment> testList = assignmentService.fetchUserAssignments(hero.getUsername());
		assertEquals(testList, herosAssignments);
	}
}
