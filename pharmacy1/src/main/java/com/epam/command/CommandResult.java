package com.epam.command;

public class CommandResult {
    private String page;
    private NavigationType navigationType;
    
	public CommandResult(String page, NavigationType navigationType) {
		this.page = page;
		this.navigationType = navigationType;
	}
	
	public CommandResult() {
		
	}
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public NavigationType getNavigationType() {
		return navigationType;
	}
	public void setNavigationType(NavigationType navigationType) {
		this.navigationType = navigationType;
	}
    
    
}
