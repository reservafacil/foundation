/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
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

package com.brazoft.foundation.gwt.client.ui;

public enum Icon
{
	GLASS("icon-glass"),
	MUSIC("icon-music"),
	SEARCH("icon-search"),
	ENVELOPE("icon-envelope"),
	HEART("icon-heart"),
	STAR("icon-star"),
	STAR_EMPTY("icon-star-empty"),
	USER("icon-user"),
	FILM("icon-film"),
	TH_LARGE("icon-th-large"),
	TH("icon-th"),
	TH_LIST("icon-th-list"),
	OK("icon-ok"),
	REMOVE("icon-remove"),
	ZOOM_IN("icon-zoom-in"),
	ZOOM_OUT("icon-zoom-out"),
	OFF("icon-off"),
	SIGNAL("icon-signal"),
	COG("icon-cog"),
	TRASH("icon-trash"),
	HOME("icon-home"),
	FILE("icon-file"),
	TIME("icon-time"),
	ROAD("icon-road"),
	DOWNLOAD_ALT("icon-download-alt"),
	DOWNLOAD("icon-download"),
	UPLOAD("icon-upload"),
	INBOX("icon-inbox"),
	PLAY_CIRCLE("icon-play-circle"),
	REPEAT("icon-repeat"),
	REFRESH("icon-refresh"),
	LIST_ALT("icon-list-alt"),
	LOCK("icon-lock"),
	FLAG("icon-flag"),
	HEADPHONES("icon-headphones"),
	VOLUME_OFF("icon-volume-off"),
	VOLUME_DOWN("icon-volume-down"),
	VOLUME_UP("icon-volume-up"),
	QRCODE("icon-qrcode"),
	BARCODE("icon-barcode"),
	TAG("icon-tag"),
	TAGS("icon-tags"),
	BOOK("icon-book"),
	BOOKMARK("icon-bookmark"),
	PRINT("icon-print"),
	CAMERA("icon-camera"),
	FONT("icon-font"),
	BOLD("icon-bold"),
	ITALIC("icon-italic"),
	TEXT_HEIGHT("icon-text-height"),
	TEXT_WIDTH("icon-text-width"),
	ALIGN_LEFT("icon-align-left"),
	ALIGN_CENTER("icon-align-center"),
	ALIGN_RIGHT("icon-align-right"),
	ALIGN_JUSTIFY("icon-align-justify"),
	LIST("icon-list"),
	INDENT_LEFT("icon-indent-left"),
	INDENT_RIGHT("icon-indent-right"),
	FACETIME_VIDEO("icon-facetime-video"),
	PICTURE("icon-picture"),
	PENCIL("icon-pencil"),
	MAP_MARKER("icon-map-marker"),
	ADJUST("icon-adjust"),
	TINT("icon-tint"),
	EDIT("icon-edit"),
	SHARE("icon-share"),
	CHECK("icon-check"),
	MOVE("icon-move"),
	STEP_BACKWARD("icon-step-backward"),
	FAST_BACKWARD("icon-fast-backward"),
	BACKWARD("icon-backward"),
	PLAY("icon-play"),
	PAUSE("icon-pause"),
	STOP("icon-stop"),
	FORWARD("icon-forward"),
	FAST_FORWARD("icon-fast-forward"),
	STEP_FORWARD("icon-step-forward"),
	EJECT("icon-eject"),
	CHEVRON_LEFT("icon-chevron-left"),
	CHEVRON_RIGHT("icon-chevron-right"),
	PLUS_SIGN("icon-plus-sign"),
	MINUS_SIGN("icon-minus-sign"),
	REMOVE_SIGN("icon-remove-sign"),
	OK_SIGN("icon-ok-sign"),
	QUESTION_SIGN("icon-question-sign"),
	INFO_SIGN("icon-info-sign"),
	SCREENSHOT("icon-screenshot"),
	REMOVE_CIRCLE("icon-remove-circle"),
	OK_CIRCLE("icon-ok-circle"),
	BAN_CIRCLE("icon-ban-circle"),
	ARROW_LEFT("icon-arrow-left"),
	ARROW_RIGHT("icon-arrow-right"),
	ARROW_UP("icon-arrow-up"),
	ARROW_DOWN("icon-arrow-down"),
	SHARE_ALT("icon-share-alt"),
	RESIZE_FULL("icon-resize-full"),
	RESIZE_SMALL("icon-resize-small"),
	PLUS("icon-plus"),
	MINUS("icon-minus"),
	ASTERISK("icon-asterisk"),
	EXCLAMATION_SIGN("icon-exclamation-sign"),
	GIFT("icon-gift"),
	LEAF("icon-leaf"),
	FIRE("icon-fire"),
	EYE_OPEN("icon-eye-open"),
	EYE_CLOSE("icon-eye-close"),
	WARNING_SIGN("icon-warning-sign"),
	PLANE("icon-plane"),
	CALENDAR("icon-calendar"),
	RANDOM("icon-random"),
	COMMENT("icon-comment"),
	MAGNET("icon-magnet"),
	CHEVRON_UP("icon-chevron-up"),
	CHEVRON_DOWN("icon-chevron-down"),
	RETWEET("icon-retweet"),
	SHOPPING_CART("icon-shopping-cart"),
	FOLDER_CLOSE("icon-folder-close"),
	FOLDER_OPEN("icon-folder-open"),
	RESIZE_VERTICAL("icon-resize-vertical"),
	RESIZE_HORIZONTAL("icon-resize-horizontal"),
	HDD("icon-hdd"),
	BULLHORN("icon-bullhorn"),
	BELL("icon-bell"),
	CERTIFICATE("icon-certificate"),
	THUMBS_UP("icon-thumbs-up"),
	THUMBS_DOWN("icon-thumbs-down"),
	HAND_RIGHT("icon-hand-right"),
	HAND_LEFT("icon-hand-left"),
	HAND_UP("icon-hand-up"),
	HAND_DOWN("icon-hand-down"),
	CIRCLE_ARROW_RIGHT("icon-circle-arrow-right"),
	CIRCLE_ARROW_LEFT("icon-circle-arrow-left"),
	CIRCLE_ARROW_UP("icon-circle-arrow-up"),
	CIRCLE_ARROW_DOWN("icon-circle-arrow-down"),
	GLOBE("icon-globe"),
	WRENCH("icon-wrench"),
	TASKS("icon-tasks"),
	FILTER("icon-filter"),
	BRIEFCASE("icon-briefcase"),
	FULLSCREEN("icon-fullscreen"),
	NONE("icon-none");
	
	private String className;
	
	private Icon(String className)
	{
		this.className = className;
	}
	
	public String className(boolean white)
	{
		return white ? this.className + " icon-white" : this.className;
	}
	
	@Override
	public String toString()
	{
		return this.className;
	}
}