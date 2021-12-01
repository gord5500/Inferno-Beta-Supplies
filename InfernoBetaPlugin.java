package net.runelite.client.plugins.betainfernosupplies;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.ArrayList;

@Slf4j
@PluginDescriptor(
	name = "InfernoBetaSupplies"
)
public class InfernoBetaPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private InfernoBetaConfig config;

	private ArrayList<String> items = new ArrayList<String>();
	private int filterNextTick = 0;

	@Override
	protected void startUp() throws Exception
	{

		items.add("<col=ff9040>Twisted bow</col>");
		items.add("<col=ff9040>Slayer helmet (i)</col>");
		items.add("<col=ff9040>Armadyl chestplate</col>");
		items.add("<col=ff9040>Armadyl chainskirt</col>");
		items.add("<col=ff9040>Ava's assembler</col>");
		items.add("<col=ff9040>Armadyl chainskirt</col>");
		items.add("<col=ff9040>Necklace of anguish</col>");
		items.add("<col=ff9040>Occult necklace</col>");
		items.add("<col=ff9040>Ring of the gods (i)</col>");
		items.add("<col=ff9040>Barrows gloves</col>");
		items.add("<col=ff9040>Kodai wand</col>");
		items.add("<col=ff9040>Dragon arrow</col>");
		items.add("<col=ff9040>Toxic blowpipe</col>");
		items.add("<col=ff9040>Toxic blowpipe (beta - dragon)</col>");
		items.add("<col=ff9040>Black chinchompa</col>");
		items.add("<col=ff9040>Devout boots</col>");
		items.add("<col=ff9040>Pegasian boots</col>");
		items.add("<col=ff9040>Bastion potion(4)</col>");
		items.add("<col=ff9040>Divine bastion potion(4)</col>");
		items.add("<col=ff9040>Sanfew serum(4)</col>");
		items.add("<col=ff9040>Anglerfish</col>");
		items.add("<col=ff9040>Rune pouch (l)</col>");
		items.add("<col=ff9040>Death rune</col>");
		items.add("<col=ff9040>Soul rune</col>");
		items.add("<col=ff9040>Blood rune</col>");
		items.add("<col=ff9040>Saradomin brew(4)</col>");
		items.add("<col=ff9040>Elysian spirit shield</col>");
		items.add("<col=ff9040>Teleport to house</col>");
	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		// 161.12
		// 161.14
		// 161.15 n 100.0 s 100.1
		if (filterNextTick == 1) {
			int relativeY = 42;
			int relativeX = 45;
			int xCount = 0;
			int yCount = 0;
			Widget nameWidget = client.getWidget(6553601);

			if (nameWidget != null) {
				nameWidget.getChild(1).setText("Nahbae's Inferno Supplies");
			}

			Widget suppliesWidget = client.getWidget(6553604);
			if (suppliesWidget != null) {

				Widget[] children = suppliesWidget.getChildren();
				for (Widget child : children) {

					if (!items.contains(child.getName())) {
						child.setHidden(true);
					} else {

						log.debug(child.getName() + " " + (relativeX * xCount) + " " + (relativeY * yCount));
						child.setRelativeX(relativeX * xCount);
						child.setRelativeY(relativeY * yCount);

						if (xCount == 9) {
							xCount = 0;
							yCount += 1;
						} else {
							xCount += 1;
						}
					}
				}
			}
			filterNextTick = 0;
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded)
	{
		Widget suppliesWidget = client.getWidget(6553604);
		if (suppliesWidget != null) {
			filterNextTick = 1;
		}
	}

	@Provides
	InfernoBetaConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InfernoBetaConfig.class);
	}
}