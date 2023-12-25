package model;

import java.io.Serializable;

public class Mark implements Serializable {
    private int attestation1;
    private int attestation2;
    private int finalMark;

    public Mark() {
        // Default constructor
    }

    public Mark(int attestation1, int attestation2, int finalMark) {
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.finalMark = finalMark;
    }

    public int getAttestation1() {
        return attestation1;
    }

    public void setAttestation1(int attestation1) {
        this.attestation1 = attestation1;
    }

    public int getAttestation2() {
        return attestation2;
    }

    public void setAttestation2(int attestation2) {
        this.attestation2 = attestation2;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(int finalMark) {
        this.finalMark = finalMark;
    }

    @Override
    public int hashCode() {
        // Implement hashCode method based on the unique attributes
        int prime = 31;
        int result = 1;
        result = prime * result + attestation1;
        result = prime * result + attestation2;
        result = prime * result + finalMark;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // Implement equals method based on the unique attributes
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Mark other = (Mark) obj;

        return attestation1 == other.attestation1 &&
                attestation2 == other.attestation2 &&
                finalMark == other.finalMark;
    }

    @Override
    public String toString() {
        // Implement toString method to display relevant information
        return "Mark{" +
                "attestation1=" + attestation1 +
                ", attestation2=" + attestation2 +
                ", finalMark=" + finalMark +
                '}';
    }
}
