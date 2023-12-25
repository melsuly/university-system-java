package model.researcher;

import java.util.Vector;

import model.ResearchPaper;
import model.ResearchProject;
import model.Student;

public class ResearcherStudent extends Student implements IResearcher {
	private Vector<ResearchPaper> researchPapers;
	private Vector<ResearchProject> researchProjects;

	public ResearcherStudent(String email, String password, String name, int id) {
		super(email, password, name, id);
		researchPapers = new Vector<>();
		researchProjects = new Vector<>();
	}

	@Override
	public Vector<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}

	@Override
	public Vector<ResearchProject> getResearchProjects() {
		return researchProjects;
	}

	@Override
	public void addResearchPaper(ResearchPaper paper) {
		researchPapers.add(paper);
	}

	@Override
	public void addResearchProject(ResearchProject project) {
		researchProjects.add(project);
	}
}
