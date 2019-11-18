package br.com.joaopaulosj.vanhackathon2019.ui.zoom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.Constants
import br.com.joaopaulosj.vanhackathon2019.Constants.APP_KEY
import br.com.joaopaulosj.vanhackathon2019.Constants.APP_SECRET
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import org.jetbrains.anko.intentFor
import us.zoom.sdk.*


class ZoomActivity : BaseActivity(), Constants, ZoomSDKInitializeListener, MeetingServiceListener, ZoomSDKAuthenticationListener {
	
	val zoomSDK = ZoomSDK.getInstance()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_zoom)
		
		zoomSDK.initialize(this, APP_KEY, APP_SECRET, "zoom.us", this)
	}
	
	private fun startZoom() {
		val meetingNo = intent.getStringExtra(AppConstants.EXTRA_ID)
		
		if (meetingNo.isEmpty()) {
			Toast.makeText(this, "You need to enter a meeting number/ vanity id which you want to join.", Toast.LENGTH_LONG).show()
			return
		}
		
		if (!zoomSDK.isInitialized) {
			Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show()
			return
		}
		
		val meetingService = zoomSDK.meetingService
		val opts = JoinMeetingOptions()
		
		// Some available options
		//		opts.no_driving_mode = true;
		opts.no_invite = true;
		//		opts.no_meeting_end_message = true;
		//		opts.no_titlebar = true;
		//		opts.no_bottom_toolbar = true;
		//		opts.no_dial_in_via_phone = true;
		//		opts.no_dial_out_to_phone = true;
		//		opts.no_disconnect_audio = true;
		//		opts.no_share = true;
		//		opts.invite_options = InviteOptions.INVITE_VIA_EMAIL + InviteOptions.INVITE_VIA_SMS;
		//		opts.no_audio = true;
		//		opts.no_video = true;
		//		opts.meeting_views_options = MeetingViewsOptions.NO_BUTTON_SHARE;
		//		opts.no_meeting_error_message = true;
		//		opts.participant_id = "participant id";
		
		val params = JoinMeetingParams()
		
		params.displayName = "JoaoPaulo"
		params.meetingNo = meetingNo
		
		meetingService.joinMeetingWithParams(this, params, opts)
	}
	
	override fun onZoomSDKInitializeResult(errorCode: Int, internalErrorCode: Int) {
		startZoom()
	}
	
	override fun onMeetingStatusChanged(meetingStatus: MeetingStatus?, errorCode: Int, internalErrorCode: Int) {
		Log.v("zomsdkjp", meetingStatus?.name ?: "null")
	}
	
	override fun onZoomSDKLoginResult(result: Long) {
	}
	
	override fun onZoomIdentityExpired() {
	}
	
	override fun onZoomSDKLogoutResult(result: Long) {
	}
	
	
}

fun Context.createZoomIntent(meetingNumber: String): Intent {
	return intentFor<ZoomActivity>(AppConstants.EXTRA_ID to meetingNumber)
}