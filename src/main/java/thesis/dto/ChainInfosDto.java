package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainInfosDto implements Dto{

    private String chain;

    private Integer blocks;

    private Integer headers;

    private String bestblockhash;

    private Long difficulty;

    private Integer verificationprogress;

    private String chainwork;

    private Boolean pruned;

    private Integer pruneheight;


    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getHeaders() {
        return headers;
    }

    public void setHeaders(Integer headers) {
        this.headers = headers;
    }

    public String getBestblockhash() {
        return bestblockhash;
    }

    public void setBestblockhash(String bestblockhash) {
        this.bestblockhash = bestblockhash;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getVerificationprogress() {
        return verificationprogress;
    }

    public void setVerificationprogress(Integer verificationprogress) {
        this.verificationprogress = verificationprogress;
    }

    public String getChainwork() {
        return chainwork;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public Boolean getPruned() {
        return pruned;
    }

    public void setPruned(Boolean pruned) {
        this.pruned = pruned;
    }

    public Integer getPruneheight() {
        return pruneheight;
    }

    public void setPruneheight(Integer pruneheight) {
        this.pruneheight = pruneheight;
    }
}
