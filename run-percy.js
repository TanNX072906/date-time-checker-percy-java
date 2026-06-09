const fs = require('fs');
const { execSync } = require('child_process');

const commonPaths = [
    'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe',
    'C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe',
    'C:\\Program Files\\Microsoft\\Edge\\Application\\msedge.exe',
    'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe',
    `${process.env.LOCALAPPDATA}\\Google\\Chrome\\Application\\chrome.exe`
];

let foundBrowserPath = null;

console.log('Searching for local Chrome/Edge/Chromium...');
for (const browserPath of commonPaths) {
    if (fs.existsSync(browserPath)) {
        foundBrowserPath = browserPath;
        console.log(`[SUCCESS] Found browser at: ${foundBrowserPath}`);
        break;
    }
}

if (foundBrowserPath) {
    console.log('Configuring Percy to use the local browser...');
    process.env.PERCY_BROWSER_EXECUTABLE = foundBrowserPath;
    process.env.PUPPETEER_EXECUTABLE_PATH = foundBrowserPath;
    process.env.PUPPETEER_SKIP_CHROMIUM_DOWNLOAD = 'true';
    process.env.PUPPETEER_SKIP_DOWNLOAD = 'true';
} else {
    console.log('[WARNING] Local browser not found. Percy will download its own Chromium binary.');
}

// Execute the Percy + Cypress command
try {
    console.log('Starting Percy + Cypress...');
    // Explicitly pass process.env to ensure variables are inherited
    execSync('npx percy exec -- cypress run', { env: process.env, stdio: 'inherit' });
} catch (error) {
    console.error('Test execution failed.');
    process.exit(1);
}
