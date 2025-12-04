import { test, expect } from '@playwright/test';

test('YouTube search for Roblox', async ({ page }) => {
  // Step 1: Go to YouTube.com
  await page.goto('https://www.youtube.com');

  // Step 2: Type "Roblox" in the search bar
  const searchInput = await page.locator('input#search');
  await searchInput.fill('Roblox');

  // Step 3: Press Enter
  await searchInput.press('Enter');

  // Step 4: Verify the URL contains "Roblox"
  await page.waitForTimeout(2000); // Wait for the page to load
  const currentUrl = page.url();
  expect(currentUrl).toContain('Roblox');
});