/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gdata.data;

import com.google.gdata.data.IOutOfLineContent;
import com.google.gdata.data.media.MediaSource;

/**
 * The IMediaContent interface defines the common interface for media content.
 * 
 * 
 */
public interface IMediaContent extends IOutOfLineContent {

	/**
	 * Returns the media source associated with the content or {@code null}.
	 */
	public MediaSource getMediaSource();

	/**
	 * Sets the media source associated with the content (may be {@code null} if no supplied content).
	 */
	public void setMediaSource(MediaSource v);
}
