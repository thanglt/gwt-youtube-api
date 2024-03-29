package com.google.youtube.client.showcase;

import com.google.gdata.client.youtube.YouTubeManager;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.ChromelessYouTubePlayer;
import com.google.youtube.client.YouTubePlayer;
import com.google.youtube.client.api.FlashPlayer.WMode;
import com.google.youtube.client.ui.controls.api.IPlayerController;
import com.google.youtube.client.ui.controls.builder.EOperations;
import com.google.youtube.client.ui.controls.builder.YouTubePlayerControllerBuilder;
import com.google.youtube.client.ui.controls.factory.youtube.YouTubePlayerControlsFactory;
import com.google.youtube.client.ui.controls.manager.PlayerControlsManager;

/**
 * @author PSimun
 */
public class ChromelessPlayerShowcase implements EntryPoint {

	private static final String DEMO_VIDEO_ID = "hqXUKxJiDls";

	@Override
	public void onModuleLoad() {

		HorizontalPanel panelHolder = new HorizontalPanel();
		RootPanel.get().add(panelHolder);

		final VerticalPanel container = new VerticalPanel();
		panelHolder.add(container);

		Label label = new Label("YouTube Player Demo");
		container.add(label);
		label = new Label("This page demonstrates the YouTube Player API's functions for Google Web Toolkit.");
		container.add(label);

		ChromelessYouTubePlayer player = new ChromelessYouTubePlayer(DEMO_VIDEO_ID);
		player.setWMode(WMode.OPAQUE);
		player.setEnableJSAPI(true);
		player.setVersion(3);
		player.setPlayerAPIId("gwt-yt-api");
		player.setWidth("640px");
		player.setHeight("320px");
		container.add(player);

		final YouTubePlayer youTubePlayer = player.getPlayer();

		label = new Label("Player controls");
		container.add(label);

		YouTubePlayerControlsFactory componentsFactory = new YouTubePlayerControlsFactory();
		YouTubePlayerControllerBuilder youTubePlayerControlerBuilder = new YouTubePlayerControllerBuilder(componentsFactory);
		final IPlayerController controler = youTubePlayerControlerBuilder.createPlayerComponents(new EOperations[] {EOperations.PLAY, EOperations.PAUSE,
				EOperations.VOLUME, EOperations.SEEK, EOperations.TIME});

		YouTubeManager youTubeManager = new YouTubeManager();
		youTubeManager.retrieveVideo(DEMO_VIDEO_ID, new AsyncCallback<VideoFeed>() {

			@Override
			public void onSuccess(VideoFeed result) {
				if (result != null && result.getEntries() != null && result.getEntries().get(0) != null) {
					controler.initializeFromEntry(result.getEntries().get(0));
					container.add((Widget) controler);
					PlayerControlsManager playerControlsManager = new PlayerControlsManager(controler, youTubePlayer);
					playerControlsManager.manageEvents();
				} else {
					GWT.log("Video " + DEMO_VIDEO_ID + " does not exists", null);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Unable to load video " + DEMO_VIDEO_ID, caught);
			}
		});
	}
}