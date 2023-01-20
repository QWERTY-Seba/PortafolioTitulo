var mb = 0
chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
	chrome.storage.local.getBytesInUse(function(Bytes){  
		mb = (Bytes / (1024*1024)).toFixed(2)
		document.getElementById("cantUso").innerText = `${mb} mb`
		console.log(Bytes)
	})		
});
// background.js
chrome.runtime.onConnect.addListener(function(devToolsConnection) {
    chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
        devToolsConnection.postMessage(request)
    });
})