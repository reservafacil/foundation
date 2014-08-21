package com.brazoft.foundation.gwt.client.util;

public class MobileUtil {

	// Rodo no Safari ou como APP ?
	public static native boolean isStandalone(String url, String name, String features) /*-{
		return $wnd.navigator.standalone;
    }-*/;

	public static native String getUserAgent() /*-{
		return navigator.userAgent.toLowerCase();
    }-*/;


	public static native boolean onIE8() /*-{
		return navigator.userAgent.indexOf("MSIE 8.0") != -1;
	}-*/;
	
	public static boolean isIPhone() {
		if (getUserAgent().contains("iphone"))
			return true;
		return false;
	}

	public static boolean isIPad() {
		if (getUserAgent().contains("ipad"))
			return true;
		return false;
	}

	public static boolean isIPod() {
		if (getUserAgent().contains("ipod"))
			return true;
		return false;
	}

	public static boolean isAndroid() {
		if (getUserAgent().contains("android"))
			return true;
		return false;
	}

	public static boolean isIPhoneOrIPod() {
		if (isIPhone())
			return true;
		if (isIPod())
			return true;
		return false;
	}

	public static boolean isMobileSafari() {
		if (isIPhone())
			return true;
		if (isIPod())
			return true;
		if (isIPad())
			return true;
		if (isAndroid())
			return true;
		return false;
	}

	public static boolean isSmallSafari() {
		if (isIPhone())
			return true;
		if (isIPod())
			return true;
		if (isAndroid())
			return true;
		return false;
	}

	public static native void hideAddresBarForced() /*-{
		// Check that the current page can be scrolled first. Pad content if necessary.
		if($doc.documentElement.scrollHeight <= $doc.documentElement.clientHeight) {
	    	// Extend body height to overflow and cause scrolling
	    	bodyTag			= $doc.getElementsByTagName('body')[0];
	    	// Viewport height at fullscreen
	    	bodyTag.style.height	= ($doc.documentElement.clientWidth / screen.width * screen.height)+'px';
		}

		// Big screen. Fixed chrome likely.
		if(screen.width > 980 || screen.height > 980) return;

		// Standalone (full screen webapp) mode
		if($wnd.navigator.standalone === true) return;

		// Page zoom or vertical scrollbars
		if($wnd.innerWidth !== $doc.documentElement.clientWidth) {
	    	// Sometimes one pixel too much. Compensate.
	    	if(($wnd.innerWidth - 1) !== $doc.documentElement.clientWidth) return;
		}

		setTimeout(function() {
			// Already scrolled?
			if($wnd.pageYOffset !== 0) return;

			// Perform autoscroll
			$wnd.scrollTo(0, 1);

			// Reset body height and scroll
			if(bodyTag !== undefined) bodyTag.style.height = $wnd.innerHeight + 'px';
			$wnd.scrollTo(0, 0);

	    }, 1000);

    }-*/;

	public static native void hideAddresBar() /*-{
		// Big screen. Fixed chrome likely.
		if(screen.width > 980 || screen.height > 980) return;

		// Standalone (full screen webapp) mode
		if($wnd.navigator.standalone === true) return;

		// Page zoom or vertical scrollbars
		if($wnd.innerWidth !== $doc.documentElement.clientWidth) {
	    	// Sometimes one pixel too much. Compensate.
	    	if(($wnd.innerWidth - 1) !== $doc.documentElement.clientWidth) return;
		}

		setTimeout(function() {
			// Already scrolled?
			if($wnd.pageYOffset !== 0) return;

			// Perform autoscroll
			$wnd.scrollTo(0, 1);

			// Reset body height and scroll
			if(bodyTag !== undefined) bodyTag.style.height = $wnd.innerHeight + 'px';
			$wnd.scrollTo(0, 0);

		}, 1000);
    }-*/;
}
