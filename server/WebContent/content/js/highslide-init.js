function initHighslide(path, width, height) {
	hs.graphicsDir = path + '/content/jq-highslide/graphics/';
    hs.align = 'center';
    hs.outlineType = 'rounded-white';
    hs.wrapperClassName = 'draggable-header';
    hs.transitions = ['expand', 'crossfade'];
	hs.useBox = true;
	hs.width = width;
	hs.height = height; 
}

