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

package ca.ualberta.CMPUT301F13T02.chooseyouradventure.test;


import java.util.ArrayList;

import android.test.InstrumentationTestCase;

import ca.ualberta.CMPUT301F13T02.chooseyouradventure.Comment;
import ca.ualberta.CMPUT301F13T02.chooseyouradventure.Decision;
import ca.ualberta.CMPUT301F13T02.chooseyouradventure.HandlerException;
import ca.ualberta.CMPUT301F13T02.chooseyouradventure.Page;
import ca.ualberta.CMPUT301F13T02.chooseyouradventure.Story;
import ca.ualberta.CMPUT301F13T02.chooseyouradventure.TextTile;

/**
 * Tests various functions of the ESHandler class
 */ 
public class ESHandlerTest extends InstrumentationTestCase {
	private ESTestHandler esHandler = new ESTestHandler();
	
	/**
	 * Tests the updateStory function
	 */
	public void testUpdateStory() {

		try {
			//Create  2 pages
			Page page1 = new Page();
			page1.addTile(new TextTile("test1"));
			page1.addTile(new TextTile("test2"));
			page1.addComment(new Comment("LOLz I CN HAZ CHEEZBUERGR?", "Geoff"));
			
			Page page2 = new Page();
			page2.addTile(new TextTile("test3"));
			page2.addTile(new TextTile("test4"));
			page2.addDecision(new Decision("Go second", page2));
			page2.addDecision(new Decision("Go first", page1));
			
			//Create story
			Story story1 = new Story();
			story1.setId("testUpdate");
			story1.addPage(page1);
			story1.addPage(page2);
			
			esHandler.updateStory(story1);
			
		
			//Get same story
			Story story2 = esHandler.getStory("testUpdate");
		
			//Compare
			assertTrue(story1.equals(story2));
		}
		catch (HandlerException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Tests adding a comment to a page
	 */
	public void testAddComment() {
		
		try {
			Story story1 = esHandler.getStory("testUpdate");
		
			System.out.println("hello");
			//Add a comment to a page
			Comment comment = new Comment("TEST COMMENT", "Geoff");
			esHandler.addComment(story1, story1.getPages().get(0), comment);
			
			//Retrieve the story, get newest comment and compare for equality
			Story story2 = esHandler.getStory("testUpdate");
			ArrayList<Comment> comments =  story2.getPages().get(0).getComments();
			assertTrue(comments.get(comments.size() - 1).equals(comment));
		}
		catch(HandlerException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Tests the updateStory function
	 */
	public void testAddStory() {

		try {
			//Create 2 pages
			Page page1 = new Page();
			page1.addTile(new TextTile("test1"));
			page1.addTile(new TextTile("test2"));

			Page page2 = new Page();
			page2.addTile(new TextTile("test3"));
			page2.addTile(new TextTile("test4"));

			//Create story
			Story story1 = new Story();
			story1.addPage(page1);
			story1.addPage(page2);

			esHandler.addStory(story1);

			//Get same story
			Story story2 = esHandler.getStory(story1.getId());

			//Compare
			assertTrue(story1.equals(story2));
		}
		catch (HandlerException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the getAllStories method
	 */
	public void testGetAllStories() {
		try {
			esHandler.getAllStories();
		}
		catch (HandlerException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test the delete story method
	 */
	public void testDeleteStory() {
		Story story = new Story();
		
		//Add a story to ES
		try {
			esHandler.addStory(story);
		}
		catch (HandlerException e) {
			e.printStackTrace();
			fail();
		}
		
		//Delete that story
		try {
			esHandler.deleteStory(story);
		}
		catch (HandlerException e) {
			e.printStackTrace();
			fail();
		}
		
		//Now try to retrieve it from ES
		try {
			esHandler.getStory(story.getId());
		}
		catch (HandlerException e) {
			e.printStackTrace();
			return;
		}	
		
		//Getting here indicates that the story was not deleted
		fail("Story not deleted");
	}
}
