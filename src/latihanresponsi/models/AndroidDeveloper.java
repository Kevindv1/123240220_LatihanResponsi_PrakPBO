package latihanresponsi.models;

public class AndroidDeveloper extends Candidate {

    public AndroidDeveloper(int id, String name, double writingScore, double codingScore, double interviewScore) {
        super(id, name, writingScore, codingScore, interviewScore);
    }

    @Override
    public double calculateFinalScore() {
        // Average score based on requirements
        return (getWritingScore() + getCodingScore() + getInterviewScore()) / 3.0;
    }

    @Override
    public String getRole() {
        return "Android Developer";
    }
}
