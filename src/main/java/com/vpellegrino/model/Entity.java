package com.vpellegrino.model;

/**
 * This is a simple bean which represents an entity row to be imported
 * 
 * @author vpellegr
 *
 */
public class Entity {
	private String ts = "";
	private String changetype = "";
	private String entcatnm = "";
	private String entnm = "";
	private String dsEntnm = "";
	private String entcatnmparent = "";
	private String dsEntnmparent = "";
	private String enttypenm = "";
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getChangetype() {
		return changetype;
	}
	public void setChangetype(String changetype) {
		this.changetype = changetype;
	}
	public String getEntcatnm() {
		return entcatnm;
	}
	public void setEntcatnm(String entcatnm) {
		this.entcatnm = entcatnm;
	}
	public String getEntnm() {
		return entnm;
	}
	public void setEntnm(String entnm) {
		this.entnm = entnm;
	}
	public String getDsEntnm() {
		return dsEntnm;
	}
	public void setDsEntnm(String dsEntnm) {
		this.dsEntnm = dsEntnm;
	}
	public String getEntcatnmparent() {
		return entcatnmparent;
	}
	public void setEntcatnmparent(String entcatnmparent) {
		this.entcatnmparent = entcatnmparent;
	}
	public String getDsEntnmparent() {
		return dsEntnmparent;
	}
	public void setDsEntnmparent(String dsEntnmparent) {
		this.dsEntnmparent = dsEntnmparent;
	}
	public String getEnttypenm() {
		return enttypenm;
	}
	public void setEnttypenm(String enttypenm) {
		this.enttypenm = enttypenm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changetype == null) ? 0 : changetype.hashCode());
		result = prime * result + ((dsEntnm == null) ? 0 : dsEntnm.hashCode());
		result = prime * result + ((dsEntnmparent == null) ? 0 : dsEntnmparent.hashCode());
		result = prime * result + ((entcatnm == null) ? 0 : entcatnm.hashCode());
		result = prime * result + ((entcatnmparent == null) ? 0 : entcatnmparent.hashCode());
		result = prime * result + ((entnm == null) ? 0 : entnm.hashCode());
		result = prime * result + ((enttypenm == null) ? 0 : enttypenm.hashCode());
		result = prime * result + ((ts == null) ? 0 : ts.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (changetype == null) {
			if (other.changetype != null)
				return false;
		} else if (!changetype.equals(other.changetype))
			return false;
		if (dsEntnm == null) {
			if (other.dsEntnm != null)
				return false;
		} else if (!dsEntnm.equals(other.dsEntnm))
			return false;
		if (dsEntnmparent == null) {
			if (other.dsEntnmparent != null)
				return false;
		} else if (!dsEntnmparent.equals(other.dsEntnmparent))
			return false;
		if (entcatnm == null) {
			if (other.entcatnm != null)
				return false;
		} else if (!entcatnm.equals(other.entcatnm))
			return false;
		if (entcatnmparent == null) {
			if (other.entcatnmparent != null)
				return false;
		} else if (!entcatnmparent.equals(other.entcatnmparent))
			return false;
		if (entnm == null) {
			if (other.entnm != null)
				return false;
		} else if (!entnm.equals(other.entnm))
			return false;
		if (enttypenm == null) {
			if (other.enttypenm != null)
				return false;
		} else if (!enttypenm.equals(other.enttypenm))
			return false;
		if (ts == null) {
			if (other.ts != null)
				return false;
		} else if (!ts.equals(other.ts))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Entity [ts=" + ts + ", changetype=" + changetype + ", entcatnm=" + entcatnm + ", entnm=" + entnm
				+ ", dsEntnm=" + dsEntnm + ", entcatnmparent=" + entcatnmparent + ", dsEntnmparent=" + dsEntnmparent
				+ ", enttypenm=" + enttypenm + "]";
	}
}
