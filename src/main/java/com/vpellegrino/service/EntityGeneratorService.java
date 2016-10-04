package com.vpellegrino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.vpellegrino.model.Entity;

/**
 * This service can be used to generate entities to export
 * 
 * @author vpellegrino
 *
 */
public class EntityGeneratorService {

	int domains = 20;
	int levels = 4;
	List<String> suffixList;
	List<String> suffixListOnlyLastLevel;

	public EntityGeneratorService(){
		this(null, null);
	}
	public EntityGeneratorService(Integer domains, Integer levels){
		this.domains = (domains != null) ? domains : this.domains;
		this.levels = (levels != null) ? levels : this.levels;
		suffixList = generateSuffix(false);
		suffixListOnlyLastLevel = generateSuffix(true);
	}

	/**
	 * Removes the last substring of the input string
	 * @param target the input string
	 * @param toRemove the substring from which we should truncate the input string
	 * @return
	 */
	private String removeLastSubstring(String target, String toRemove){
		int idx = target.lastIndexOf(toRemove);
		target = target.substring(0, idx);
		return target;
	}

	/**
	 * Generates a hierarchic sequence of labels
	 * @param a final list of labels
	 * @param prevAdded last iteration generated labels
	 * @param currentLevel current hierarchic level
	 * @return a list of string, representing the labels
	 */
	private List<String> generateSequence(List<String> a, List<String> prevAdded, int currentLevel) {
		List<String> newlyAdded = new ArrayList<String>();
		if (currentLevel == 1) {
			for (int i=1; i<=this.domains; i++) {
				String val = (i<10) ? "0"+i : ""+i;
				String ent = "-"+val;
				a.add(ent);
				newlyAdded.add(ent);
			}
		} else {
			for (String aItem : prevAdded) {
				for (int i=1; i<=this.domains; i++) {
					String val = (i<10) ? "0"+i : ""+i;
					String ent = aItem+"-"+val;
					a.add(ent);
					newlyAdded.add(ent);
				}
			}
		}
		return newlyAdded;
	}

	/**
	 * Generates a hierarchic list of suffixes
	 * @param onlyLastLevel true to return only the last level
	 * @return a list of string, representing the suffixes
	 */
	private List<String> generateSuffix(boolean onlyLastLevel) {
		List<String> results = new ArrayList<String>();
		List<String> newlyAdded = new ArrayList<String>();
		for (int i=1; i<=this.levels; i++) {
			newlyAdded = generateSequence(results, newlyAdded, i);
		}
		if (onlyLastLevel) {
			return newlyAdded;
		}
		return results;
	}

	/**
	 * Generates a collection of domains
	 * @return a list of entity
	 */
	public List<Entity> declareDomains() {
		List<Entity> declaredDomains = new ArrayList<Entity>();
		for (String suffix : this.suffixList) {
			Entity entity = new Entity();
			entity.setTs(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
			entity.setChangetype("ASSERT");
			entity.setEntcatnm("APP");
			entity.setEntnm("Domain"+suffix);
			entity.setDsEntnm("Domain"+suffix);
			declaredDomains.add(entity);
		}
		return declaredDomains;
	}

	/**
	 * Generates a collection of relationship between domains
	 * @return a list of entity
	 */
	public List<Entity> setDomainsParentship() {
		List<Entity> domainParentships = new ArrayList<Entity>();
		List<String> suffixList = this.suffixList;
		for (int i=0; i<suffixList.size(); i++) {
			String suffix = suffixList.get(i);
			Entity entity = new Entity();
			entity.setTs(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
			entity.setChangetype("ASSERT");
			entity.setEntcatnm("APP");
			entity.setEntnm("Domain"+suffix);
			entity.setDsEntnm("Domain"+suffix);
			/* setting parentship */
			entity.setEntcatnmparent("APP");
			if (i<domains) {
				/* root node */
				entity.setDsEntnmparent("_ROOTAPP_");
			} else {
				/* node of previous level */
				String currentEntnm = entity.getEntnm();
				String parentEntnm = removeLastSubstring(currentEntnm, "-");
				entity.setDsEntnmparent(parentEntnm);

			}
			domainParentships.add(entity);
		}
		return domainParentships;
	}

	/**
	 * Generates a collection of vms
	 * @return a list of entity
	 */
	public List<Entity> declareVMs() {
		List<Entity> vms = new ArrayList<Entity>();
		List<String> suffixList = this.suffixListOnlyLastLevel;
		for (int i=0; i<suffixList.size(); i++) {
			/* retrieved suffix list only for the last level */
			/* in 0-index-based array, odd indexes refer to even elements */
			if (i%2==1) {
				String suffix = suffixList.get(i);
				Entity entity = new Entity();
				entity.setTs(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
				entity.setChangetype("ASSERT");
				entity.setEntcatnm("SYS");
				entity.setEntnm("vm"+suffix);
				entity.setDsEntnm("vm"+suffix);
				entity.setEnttypenm("gm:vmw");
				vms.add(entity);
			}
		}
		return vms;
	}

	/**
	 * Generates a collection of relationship between a vm and a domain
	 * @return a list of entity
	 */
	public List<Entity> setVMsParentship() {
		List<Entity> vmsParentships = new ArrayList<Entity>();
		List<String> suffixList = this.suffixListOnlyLastLevel;
		for (int i=0; i<suffixList.size(); i++) {
			/* retrieved suffix list only for the last level */
			/* in 0-index-based array, odd indexes refer to even elements */
			if (i%2==1) {
				String suffix = suffixList.get(i);
				Entity entity = new Entity();
				entity.setTs(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
				entity.setChangetype("ASSERT");
				entity.setEntcatnm("SYS");
				entity.setEntnm("vm"+suffix);
				entity.setDsEntnm("vm"+suffix);
				/* setting parentship */
				entity.setEntcatnmparent("APP");
				entity.setDsEntnmparent("Domain"+suffix);
				vmsParentships.add(entity);
			}
		}
		return vmsParentships;
	}
}
