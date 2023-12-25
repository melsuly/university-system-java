package model.researcher;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import model.ResearchPaper;
import model.ResearchProject;

public interface IResearcher {
    Vector<ResearchPaper> getResearchPapers();

    Vector<ResearchProject> getResearchProjects();

    /**
     * Adds a research paper to the researcher's collection.
     *
     * @param paper The research paper to add.
     */
    void addResearchPaper(ResearchPaper paper);

    /**
     * Adds a research project to the researcher's collection.
     *
     * @param project The research project to add.
     */
    void addResearchProject(ResearchProject project);

    /**
     * Prints the researcher's papers in sorted order.
     * The sorting is based on the provided Comparator.
     *
     * @param comparator The Comparator to dictate the order of papers.
     */
    default void printPapers(Comparator<ResearchPaper> comparator) {
        getResearchPapers().stream()
                .sorted(comparator)
                .forEach(paper -> System.out.println(paper));
    }

    /**
     * Calculates the researcher's h-index.
     *
     * @return The h-index of the researcher.
     */
    default int calculateHIndex() {
        Vector<ResearchPaper> papers = getResearchPapers();
        if (papers.isEmpty()) {
            return 0;
        }

        // Sort papers in descending order of citations
        Collections.sort(papers, Comparator.comparing(ResearchPaper::getCitations).reversed());

        int hIndex = 0;
        for (int i = 0; i < papers.size(); i++) {
            int rank = i + 1;
            int citationCount = papers.get(i).getCitations();

            if (citationCount >= rank) {
                hIndex = rank;
            } else {
                break;
            }
        }
        return hIndex;
    }
}
