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

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * A photo Tile for use in stories. PhotoTile is a concrete implementation of the abstract
 * class Tile.
 * 
 * PhotoTile is a member of this application's model, it is included in the JSON when the story
 * is serialized and stored via the use of the handlers. A PhotoTile will contain an image to be displayed
 * in the story.
 * 
 */

public class PhotoTile extends Tile{
	private final String type = "photo";
	private byte[] imageData;
	
	
	/**
	 * Get the type
	 * @return The type
	 */
	public String getType() {
		return type;
	}


	

	/**
	 * @return the image
	 */
	public Bitmap getImage() {
		return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
	}

	/**
	 * Sets both the image and imageData parameter via. conversion
	 * @param imageData the imageData to set
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
		
	}
	
	public byte[] getImageData() {
		return imageData;
	}

	/**
	 * Sets the content to the passed content
	 * 
	 * @param content The content to update this tile to
	 */
	@Override
	public void setContent(Object content) {
		Bitmap image = (Bitmap) content;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		imageData =  outStream.toByteArray();
	}
    
}
