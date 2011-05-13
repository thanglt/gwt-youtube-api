package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasVolumeChangeHandler extends HasHandlers {
	HandlerRegistration addVolumeHandler(VolumeChangeHandler handler);
}
