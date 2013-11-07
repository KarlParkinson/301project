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

/**
 * A text Tile for use in stories. TextTile is a concrete implementation of the abstract
 * class Tile.
 * 
 * TextTile is a member of this application's model, it is included in the JSON when the story
 * is serialized and stored via the use of the handlers. A TextTile will contain an image to be displayed
 * in the story.
 */

public class TextTile extends Tile{
    private String text;
    private final String type = "text";
    
    /**
     * Constructor
     * 
     * @param text The text to display in this tile
     */
    public TextTile(String text) {
    	this.text = text;
    }
    
    public TextTile() {
    	this.text = "New Text Block";
    }
    
    /**
     * Returns this tile's text
     * 
     * @return This tile's text
     */
    public String getText() {
    	return text;
    }
    
    /**
     * Tests the equality of this tile and the passed tile
     * 
     * @param tile The tile to compare against
     * @return Whether or not the two tiles are considered equal
     */
    public boolean equals(Tile tile) {
    	if (tile instanceof TextTile)
	    	return text.equals(((TextTile)tile).getText());
    	else
    		return false;
    }

	public String getType() {
		return type;
	}
	
	@Override
	public void setContent(Object content) {
		String text = (String) content;
		this.text = text;
	}
}
