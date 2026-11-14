# Minecraft Auction House Plugin: FoXAuctionReplica

FoXAuctionReplica is a lightweight Minecraft auction house plugin that adds a DonutSMP-style auction house replica with the `/ah` and `/foxah` commands, a GUI menu, replica auction listings, a FoX Spawn Egg display item, and rotating orange particles around players while the auction house is open.

## Install

1. Build the plugin with Java 21 and Maven:

   ```bash
   mvn package
   ```

2. Copy the generated `plugin.jar` file from the `target` directory into your Minecraft server's `plugins` directory.

3. Start or restart the server.

4. Use a Paper Minecraft server running version 1.21 or newer. The plugin is compiled against Paper 1.21.11.

## Use

1. Join the server as a player.
2. Run `/ah` or `/foxah`.
3. View the auction house replica menu.
4. The menu displays:
   - A FoX Spawn Egg priced at 1,000,000 coins
   - A Sharpness Sword priced at 250,000 coins
   - An Enchanted Golden Apple priced at 75,000 coins
   - A Netherite Ingot priced at 100,000 coins
5. Click the barrier button in slot 22 or close the inventory normally.
6. Orange particles rotate around the player while the auction house menu is open.
7. The displayed listings are replica display items and do not process purchases.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/30fdb6c7-1a92-473b-aa94-c5411a269f48?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=30fdb6c7-1a92-473b-aa94-c5411a269f48
