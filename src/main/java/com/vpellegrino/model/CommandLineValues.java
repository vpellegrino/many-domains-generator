package com.vpellegrino.model;

import org.kohsuke.args4j.Option;

/**
 * This is a simple bean that represents the command line values, eventually
 * passed to main method when main class is launched
 * 
 * @author vpellegrino
 *
 */
public class CommandLineValues {

    @Option(name = "-d", aliases = {
    "--domains" }, required = false, usage = "number of domains")
    private Integer domains;

    @Option(name = "-l", aliases = {
    "--levels" }, required = false, usage = "number of levels")
    private Integer levels;

	public Integer getDomains() {
		return domains;
	}

	public void setDomains(Integer domains) {
		this.domains = domains;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	@Override
	public String toString() {
		return "CommandLineValues [domains=" + domains + ", levels=" + levels + "]";
	}

}
