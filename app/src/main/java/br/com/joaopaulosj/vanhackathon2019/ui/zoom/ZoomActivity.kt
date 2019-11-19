package br.com.joaopaulosj.vanhackathon2019.ui.zoom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.Constants
import br.com.joaopaulosj.vanhackathon2019.Constants.APP_KEY
import br.com.joaopaulosj.vanhackathon2019.Constants.APP_SECRET
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import org.jetbrains.anko.intentFor
import us.zoom.sdk.*


class ZoomActivity : BaseActivity(), Constants, ZoomSDKInitializeListener,
		MeetingServiceListener, ZoomSDKAuthenticationListener, InMeetingServiceListener {
	
	val zoomSDK = ZoomSDK.getInstance()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_zoom)
		
		zoomSDK.initialize(this, APP_KEY, APP_SECRET, "zoom.us", this)
	}
	
	private fun startZoom() {
		val meetingNo = intent.getStringExtra(AppConstants.EXTRA_ID)
		
		zoomSDK.inMeetingService.addListener(this)
		
		if (meetingNo.isEmpty()) {
			Toast.makeText(this, "You need to enter a meeting number/vanity id which you want to join.", Toast.LENGTH_LONG).show()
			return
		}
		
		if (!zoomSDK.isInitialized) {
			Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show()
			return
		}
		
		val meetingService = zoomSDK.meetingService
		meetingService.addListener(this)
		val opts = JoinMeetingOptions()
		
		opts.no_invite = true;
		
		val params = JoinMeetingParams()
		params.displayName = "Joao Paulo"
		params.meetingNo = meetingNo
		
		meetingService.joinMeetingWithParams(this, params, opts)
	}
	
	override fun onZoomSDKInitializeResult(errorCode: Int, internalErrorCode: Int) {
		startZoom()
	}
	
	override fun onMeetingStatusChanged(meetingStatus: MeetingStatus?, errorCode: Int, internalErrorCode: Int) {
	}
	
	override fun onZoomSDKLoginResult(result: Long) {
	}
	
	override fun onMeetingUserLeave(userList: MutableList<Long>?) {
		if (userList?.any { it == zoomSDK.inMeetingService.myUserID } == true) {
			finish()
		}
	}
	
	override fun onJoinWebinarNeedUserNameAndEmail(handler: InMeetingEventHandler?) {
	
	}
	
	override fun onActiveVideoUserChanged(userId: Long) {
	}
	
	override fun onActiveSpeakerVideoUserChanged(userId: Long) {
	}
	
	override fun onChatMessageReceived(msg: InMeetingChatMessage?) {
	}
	
	override fun onUserNetworkQualityChanged(userId: Long) {
	}
	
	override fun onMeetingUserJoin(userList: MutableList<Long>?) {
	}
	
	override fun onMeetingFail(errorCode: Int, internalErrorCode: Int) {
	}
	
	override fun onUserAudioTypeChanged(userId: Long) {
	}
	
	override fun onMyAudioSourceTypeChanged(type: Int) {
	}
	
	override fun onSilentModeChanged(inSilentMode: Boolean) {
	}
	
	override fun onMeetingCoHostChanged(userId: Long) {
	}
	
	override fun onLowOrRaiseHandStatusChanged(userId: Long, isRaiseHand: Boolean) {
	}
	
	override fun onMeetingUserUpdated(userId: Long) {
	}
	
	override fun onMeetingSecureKeyNotification(key: ByteArray?) {
	}
	
	override fun onMeetingNeedColseOtherMeeting(handler: InMeetingEventHandler?) {
	}
	
	override fun onMicrophoneStatusError(error: InMeetingAudioController.MobileRTCMicrophoneError?) {
	}
	
	override fun onWebinarNeedRegister() {
	}
	
	override fun onSpotlightVideoChanged(on: Boolean) {
	}
	
	override fun onMeetingHostChanged(userId: Long) {
	}
	
	override fun onMeetingLeaveComplete(ret: Long) {
		finish()
	}
	
	override fun onHostAskUnMute(userId: Long) {
	}
	
	override fun onUserAudioStatusChanged(userId: Long) {
	}
	
	override fun onMeetingNeedPasswordOrDisplayName(needPassword: Boolean, needDisplayName: Boolean, handler: InMeetingEventHandler?) {
	}
	
	override fun onUserVideoStatusChanged(userId: Long) {
	}
	
	override fun onZoomIdentityExpired() {
	}
	
	override fun onZoomSDKLogoutResult(result: Long) {
	}
}

fun Context.createZoomIntent(meetingNumber: String): Intent {
	return intentFor<ZoomActivity>(AppConstants.EXTRA_ID to meetingNumber)
}