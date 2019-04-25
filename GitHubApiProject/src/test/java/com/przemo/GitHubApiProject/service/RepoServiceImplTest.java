package com.przemo.GitHubApiProject.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.przemo.GitHubApiProject.entity.Repo;
import com.przemo.GitHubApiProject.exception.LackOfRepositoriesExcpetion;

public class RepoServiceImplTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	RepositoryService repositoryServiceMock;
	
	@InjectMocks
	RepoServiceImpl repoImpl;
	
	//set company name
	private static String company  = "allegro";
	
	
	@Test
	public void test_GetLatestModifiedRepository_Should_RetrunLatestUpdatedRepositoryName() throws IOException 
	{
		//Given
			//Create mock Repositories  and set their update date
		Repository repo_latestUpdated = new Repository();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 12, 12);
		repo_latestUpdated.setUpdatedAt(calendar.getTime());
		repo_latestUpdated.setName("LatestUpdate");
				
		Repository r2 = new Repository();
		calendar.set(1990, 12, 12);
		r2.setUpdatedAt(calendar.getTime());
			//create mock list of repos
		List<Repository> repositories = new ArrayList<Repository>(Arrays.asList(repo_latestUpdated,r2));
		
		given(repositoryServiceMock.getRepositories(company)).willReturn(repositories);
		
		//When
		String name = repoImpl.getLatestModifiedRepository(company).getRepo_name();
		
		//Then
		assertThat(name, is(repo_latestUpdated.getName()));
		
	}
	
	@Test(expected = LackOfRepositoriesExcpetion.class)
	public void test_GetLatestModifiedRepository_Should_throwLackOfRepositoryException() throws IOException
	{
		given(repositoryServiceMock.getRepositories(company)).willReturn(new ArrayList<Repository>());
		
		Repo repo = repoImpl.getLatestModifiedRepository(company);

	}
	
	@Test(expected = NullPointerException.class)
	public void test_GetLatestModifiedRepository_Should_throwNullPointerException() throws IOException
	{
		given(repositoryServiceMock.getRepositories(company)).willReturn(null);
		
		Repo repo = repoImpl.getLatestModifiedRepository(company);
	}

}
