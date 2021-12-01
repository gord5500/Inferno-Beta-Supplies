package net.runelite.client.plugins.betainfernosupplies;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("InfernoBetaSupplies")
public interface InfernoBetaConfig extends Config
{
	@ConfigItem(
		keyName = "InfernoBeta",
		name = "Shoutout",
		description = ""
	)
	default String greeting()
	{
		return "Naabe";
	}
}