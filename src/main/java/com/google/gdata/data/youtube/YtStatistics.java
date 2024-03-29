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

package com.google.gdata.data.youtube;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "statistics")
public class YtStatistics extends ExtensionPoint {
	@Field
	private long viewCount;
	@Field
	private long favoriteCount;

	/** Gets view count, 0 by default. */
	public long getViewCount() {
		return viewCount;
	}

	/** Sets view count. */
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	/** Gets favorite count, 0 by default. */
	public long getFavoriteCount() {
		return favoriteCount;
	}

	/** Sets favorite count. */
	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

}
