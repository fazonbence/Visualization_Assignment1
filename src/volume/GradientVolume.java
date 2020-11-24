/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package volume;

/**
 *
 * @author michel
 */
public class GradientVolume {

    public GradientVolume(Volume vol) {
        volume = vol;
        dimX = vol.getDimX();
        dimY = vol.getDimY();
        dimZ = vol.getDimZ();
        data = new VoxelGradient[dimX * dimY * dimZ];
        compute();
        maxmag = -1.0;
    }

    public VoxelGradient getGradient(int x, int y, int z) {
        return data[x + dimX * (y + dimY * z)];
    }

    public void setGradient(int x, int y, int z, VoxelGradient value) {
        data[x + dimX * (y + dimY * z)] = value;
    }

    public void setVoxel(int i, VoxelGradient value) {
        data[i] = value;
    }

    public VoxelGradient getVoxel(int i) {
        return data[i];
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getDimZ() {
        return dimZ;
    }

    /**
     * Computes the gradient information of the volume according to Levoy's
     * paper.
     */
    private void compute() {
        //TODO 4: Implement gradient computation.


        // this just initializes all gradients to the vector (0,0,0)
        for (int i = 0; i < data.length; i++) {
            data[i] = zero;
        }

        //FLAG we don't have to compute the edges, however we need to give an explanation for this
        //FLAG is s the voxel value?
        //Computes the gradients for the vector
        for(int x = 1;x<dimX-1;x++){
            for(int y = 1;y<dimY-1;y++){
                for(int z = 01;z<dimZ-1;z++){
                   // System.out.println(volume.getVoxel(x, y, z));
                    float s_x =(float) 0.5*(volume.getVoxel(x+1,y,z)-volume.getVoxel(x-1,y,z));
                    float s_y = (float) 0.5*(volume.getVoxel(x,y+1,z)-volume.getVoxel(x,y-1,z));
                    float s_z = (float) 0.5*(volume.getVoxel(x,y,z+1)-volume.getVoxel(x,y,z-1));
                    VoxelGradient result = new VoxelGradient(s_x, s_y, s_z);
                    setGradient(x, y, z, result);
                }
            }
        }
    }

    public double getMaxGradientMagnitude() {
        if (maxmag >= 0) {
            return maxmag;
        } else {
            double magnitude = data[0].mag;
            for (int i = 0; i < data.length; i++) {
                magnitude = data[i].mag > magnitude ? data[i].mag : magnitude;
            }
            maxmag = magnitude;
            return magnitude;
        }
    }

    private int dimX, dimY, dimZ;
    private VoxelGradient zero = new VoxelGradient();
    VoxelGradient[] data;
    Volume volume;
    double maxmag;
}
