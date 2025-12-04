import { test, expect } from '@playwright/test';

test('Verify Client Work Page', async ({ page }) => {
  // Step 1: Go to spam.com
  await page.goto('https://spam.com');

  // Step 2: Select "Services" from the header menu
  await page.click('text=Services');

  // Step 3: Click "Explore Our Client work" link
  await page.click('text=Explore Our Client work');

  // Step 4: Verify that the "Client Work" text is visible on the page
  const clientWorkText = await page.locator('text=Client Work');
  await expect(clientWorkText).toBeVisible();
});