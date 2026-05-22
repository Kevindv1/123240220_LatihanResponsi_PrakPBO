package latihanresponsi.models;

public abstract class Candidate implements ICandidate {
    private int id;
    private String name;
    private double writingScore;
    private double codingScore;
    private double interviewScore;

    public Candidate(int id, String name, double writingScore, double codingScore, double interviewScore) {
        this.id = id;
        this.name = name;
        this.writingScore = writingScore;
        this.codingScore = codingScore;
        this.interviewScore = interviewScore;
    }

    // Encapsulation via Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWritingScore() { return writingScore; }
    public void setWritingScore(double writingScore) { this.writingScore = writingScore; }

    public double getCodingScore() { return codingScore; }
    public void setCodingScore(double codingScore) { this.codingScore = codingScore; }

    public double getInterviewScore() { return interviewScore; }
    public void setInterviewScore(double interviewScore) { this.interviewScore = interviewScore; }

    @Override
    public String determineStatus() {
        if (calculateFinalScore() >= 85) {
            return "DITERIMA";
        } else {
            return "TIDAK DITERIMA";
        }
    }

    public abstract String getRole();
}
