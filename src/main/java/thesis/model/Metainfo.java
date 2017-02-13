package thesis.model;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Entity
@Table(name = "metainfos")
public class Metainfo extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_metainfo_id")
    @SequenceGenerator(initialValue = 1, name = "seq_metainfo_id", sequenceName = "seq_metainfo_id")
    @Column(name = "run_id")
    private Integer runId;

    private Timestamp startTime;
    private Timestamp endTime;
    private int extracted_records;
    private int ids;
    private int domains;
    private int extracted_transactions;
    private int extracted_blocks;
    private boolean successful;
    private String notes;
    private int startBlock;
    private int endBlock;


    public Metainfo() {
    }

    public Metainfo(int runId) {
        this.runId = runId;
    }

    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getExtracted_records() {
        return extracted_records;
    }

    public void setExtracted_records(int extracted_records) {
        this.extracted_records = extracted_records;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getDomains() {
        return domains;
    }

    public void setDomains(int domains) {
        this.domains = domains;
    }

    public int getExtracted_transactions() {
        return extracted_transactions;
    }

    public void setExtracted_transactions(int extracted_transactions) {
        this.extracted_transactions = extracted_transactions;
    }

    public int getExtracted_blocks() {
        return extracted_blocks;
    }

    public void setExtracted_blocks(int extracted_blocks) {
        this.extracted_blocks = extracted_blocks;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(int startBlock) {
        this.startBlock = startBlock;
    }

    public int getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(int endBlock) {
        this.endBlock = endBlock;
    }

    @Override
    public String toString() {
        return "Metainfo [ \n runId=" + runId + ",\n start=" + startTime + ",\n end=" + endTime
                + ",\n extracted_records=" + extracted_records + ",\n ids=" + ids
                + ",\n domains=" + domains + ",\n extracted_transactions="
                + extracted_transactions + ",\n extracted_blocks="
                + extracted_blocks + ",\n successful=" + successful + ",\n notes="
                + notes + ",\n startBlock=" + startBlock + ",\n endBlock="
                + endBlock + "\n ]";
    }

}
