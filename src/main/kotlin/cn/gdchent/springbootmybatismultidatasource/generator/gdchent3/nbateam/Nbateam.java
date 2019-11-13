package cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam;

import java.io.Serializable;

/**
 * nbateam
 * @author 
 */
public class Nbateam implements Serializable {
    /**
     * nba球队id
     */
    private Integer nbaId;

    /**
     * 球队名称
     */
    private String nbaName;

    /**
     * 球员名字
     */
    private String nbaPlayer;

    private static final long serialVersionUID = 1L;

    public Integer getNbaId() {
        return nbaId;
    }

    public void setNbaId(Integer nbaId) {
        this.nbaId = nbaId;
    }

    public String getNbaName() {
        return nbaName;
    }

    public void setNbaName(String nbaName) {
        this.nbaName = nbaName;
    }

    public String getNbaPlayer() {
        return nbaPlayer;
    }

    public void setNbaPlayer(String nbaPlayer) {
        this.nbaPlayer = nbaPlayer;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Nbateam other = (Nbateam) that;
        return (this.getNbaId() == null ? other.getNbaId() == null : this.getNbaId().equals(other.getNbaId()))
            && (this.getNbaName() == null ? other.getNbaName() == null : this.getNbaName().equals(other.getNbaName()))
            && (this.getNbaPlayer() == null ? other.getNbaPlayer() == null : this.getNbaPlayer().equals(other.getNbaPlayer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNbaId() == null) ? 0 : getNbaId().hashCode());
        result = prime * result + ((getNbaName() == null) ? 0 : getNbaName().hashCode());
        result = prime * result + ((getNbaPlayer() == null) ? 0 : getNbaPlayer().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nbaId=").append(nbaId);
        sb.append(", nbaName=").append(nbaName);
        sb.append(", nbaPlayer=").append(nbaPlayer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}