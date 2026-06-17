const fs = require('fs');
const path = require('path');

const commonPaths = [
    'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe',
    'C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe',
    'C:\\Program Files\\Microsoft\\Edge\\Application\\msedge.exe',
    'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe',
    `${process.env.LOCALAPPDATA}\\Google\\Chrome\\Application\\chrome.exe`
];

let executablePath = null;
for (const browserPath of commonPaths) {
    if (fs.existsSync(browserPath)) {
        executablePath = browserPath;
        console.log(`[Percy Config] Found local browser at: ${executablePath}`);
        break;
    }
}

if (executablePath) {
    // Set environment variables dynamically when this config is loaded
    process.env.PERCY_BROWSER_EXECUTABLE = executablePath;
    process.env.PUPPETEER_EXECUTABLE_PATH = executablePath;
    process.env.PUPPETEER_SKIP_CHROMIUM_DOWNLOAD = 'true';
    process.env.PUPPETEER_SKIP_DOWNLOAD = 'true';
} else {
    console.log('[Percy Config] Warning: Local browser not found. Percy may download Chromium.');
}

module.exports = {
  version: 2,
  discovery: {
    executable: executablePath
  }
};
