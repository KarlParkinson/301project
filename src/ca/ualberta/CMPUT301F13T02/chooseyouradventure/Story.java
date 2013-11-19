/*
* Copyright (c) 2013, TeamCMPUT301F13T02
* All rights reserved.
* 
* Redistribution and use in source and binary forms, with or without modification,
* are permitted provided that the following conditions are met:
* 
* Redistributions of source code must retain the above copyright notice, this
* list of conditions and the following disclaimer.
* 
* Redistributions in binary form must reproduce the above copyright notice, this
* list of conditions and the following disclaimer in the documentation and/or
* other materials provided with the distribution.
* 
* Neither the name of the {organization} nor the names of its
* contributors may be used to endorse or promote products derived from
* this software without specific prior written permission.
* 
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ca.ualberta.CMPUT301F13T02.chooseyouradventure;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents a story -- it is part of the model of the application.
 * 
 * A Story is serialized and stored via a Handler implementation which interact
 * solely with Storys.  
 * 
 */
public class Story {
	
    private ArrayList<Page> pages = new ArrayList<Page>();
    private String id;
    private UUID firstpage; 
    private int currRefNum = 1;
    private String author;
	private String title = new String();
	@SuppressWarnings("unused")
	private long timeStamp;
	
	
	private boolean usesCombat = false;
	private Counters playerStats;
	
	private transient Handler handler;


	/**
	 * @return the hpCount
	 */
	
	/**
	 * This is the main constructor for Story
	 */
	public Story() {
		this.firstpage = new Page().getId();
		this.timeStamp = System.currentTimeMillis() / 1000L;
	}

	/**
	 * This is a copy constructor to copy a story 
	 */
	public Story(Story story) {
		this.firstpage = new Page().getId();
		
	}


	/**
	 * @param firstpage the firstpage to set
	 */
	public void setFirstpage(UUID firstpage)
	{
		this.firstpage = firstpage;
	}

	/**
	 * @return the firstpage
	 */
	public Page getFirstpage()
	{
		Page fp = new Page();
		for(int i = 0; i < pages.size(); i++){
			if (firstpage.equals(pages.get(i).getId()))
			{
				fp = pages.get(i);
			}
		}
		return fp;

	}

	/**
	 * This sets the title of the story
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This gets the title of the story
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This sets the ID of the story
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * This gets the ID of the story
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This sets the author of the story. Note that the author string should
	 * be a string generated by the android device that is unique to that 
	 * app on that device. This allows the app to distinguish users.
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * This gets the author of the story
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * This gets the currRefNum
	 * @return
	 */
	public int getCurrRefNum() {
		return currRefNum;
	}

	/**
     * This gets the pages of a story
     * @return The pages
     */
    public ArrayList<Page> getPages() {
    	return pages;
    }

    /**
     * This adds a page to a story
     * @param newPage A new page
     */
    public void addPage(Page newPage) {
    	newPage.setRefNum(currRefNum);
    	pages.add(newPage);
    	currRefNum++;
    }

    /**
     * This deletes a page from a story
     * @param aPage What to delete
     */
    public void deletePage(Page aPage) {
    	
    }
    /**
	 * This function updates the stories data in the database
	 */
	public void updateStory(){
		try {
			handler.updateStory(this);
		} catch (HandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This sets the scope of the storage of a story
	 * @param state The type of Handler to set our handler to
	 */
	public void setHandler(Handler state) {
		handler = state;
	}
	/**
	 * This gets the scope of the storage of a story
	 * @return state The type of Handler we are using
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * Compares this story for deep equality with another story
	 * @param The story to test equality with
	 * @return The equality Truth value
	 */
	public boolean equals(Story story) {
	
		if (pages.size() != story.getPages().size())
			return false;
		
		if (!title.equals(story.getTitle()))
			return false;
		
		//if (!firstpage.equals(story.getFirstpage()))
		//	return false;
		
		//Check that all comments are the same
		for (int i = 0; i < pages.size(); i++) {
			if (!pages.get(i).equals(story.getPages().get(i))) 
				return false;
		}
		return true;
	}

	public boolean isUsesCombat() {
		return usesCombat;
	}

	public void setUsesCombat(boolean usesCombat) {
		this.usesCombat = usesCombat;
	}

	public Counters getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(Counters playerStats) {
		this.playerStats = playerStats;
	}
	
	
	
	 
	
}
