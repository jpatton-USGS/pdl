package gov.usgs.earthquake.dyfi;

import gov.usgs.earthquake.indexer.DefaultIndexerModule;
import gov.usgs.earthquake.indexer.IndexerModule;
import gov.usgs.earthquake.indexer.ProductSummary;
import gov.usgs.earthquake.product.Product;

public class DYFIIndexerModule extends DefaultIndexerModule {

	@Override
	public int getSupportLevel(Product product) {
		int supportLevel = IndexerModule.LEVEL_UNSUPPORTED;
		if (getBaseProductType(product.getId().getType()).startsWith("dyfi")
				&& product.getContents().containsKey(
						DYFIProduct.DYFI_EVENT_XML_ATTACHMENT)) {
			supportLevel = IndexerModule.LEVEL_SUPPORTED;
		}
		return supportLevel;
	}

	@Override
	public ProductSummary getProductSummary(Product product) throws Exception {
		// DYFI-specific properties load through the DYFIProduct.
		ProductSummary summary = super.getProductSummary(new DYFIProduct(
				product));
		return summary;
	}
}
